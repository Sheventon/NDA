package ru.itis.usersservice.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * created: 13-07-2021 - 16:28
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class FreemarkerMailMessageGeneratorImpl implements MailMessageGenerator {

    private final Configuration freemarkerConfiguration;

    public FreemarkerMailMessageGeneratorImpl(Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    @Override
    public String getMailForConfirm(final String confirmCode, final String path) {
        Template mailTemplate = loadTemplate(path);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("confirm_code", confirmCode);

        return createMessage(mailTemplate, attributes);
    }

    private Template loadTemplate(final String path) {
        try {
             return freemarkerConfiguration.getTemplate(path);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String createMessage(Template template, Map<String, Object> attributes) {
        try {
            StringWriter stringWriter = new StringWriter();
            template.process(attributes, stringWriter);

            return stringWriter.toString();
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
