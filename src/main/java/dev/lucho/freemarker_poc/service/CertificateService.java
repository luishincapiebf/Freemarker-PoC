package dev.lucho.freemarker_poc.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import dev.lucho.freemarker_poc.model.TemplateDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Service
public class CertificateService {

    @Autowired
    private TemplateService templateService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public byte[] createCertificate(UUID id) throws TemplateException, IOException, DocumentException {
        TemplateDTO template = templateService.get(id);

        Configuration configuration = freeMarkerConfigurer.getConfiguration();

        Template template1 = new Template(
                template.getName(),
                template.getContent(),
                configuration
        );

        // Process the template and generate PDF
        StringWriter stringWriter = new StringWriter();

        // Create dataModel
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("logo", "https://placehold.co/200x25/F1F1F1/000?text=Blankfactor&font=raleway");

        Map<String, Object> certificate = new HashMap<>();
        certificate.put("expeditionDate", "2021-01-01");
        certificate.put("addressedTo", "Mike Doe");
        certificate.put("withSalary", false);


        dataModel.put("certificate", certificate);

        Map<String, Object> employee = new HashMap<>();
        employee.put("names", "Big Joe");
        employee.put("governmentId", "123456789");
        employee.put("position", "Software Engineer");
        employee.put("startDate", "2021-01-01");
        employee.put("salaryInLetters", "One thousand");
        employee.put("salaryInNumbers", "1000");

        dataModel.put("employee", employee);

        Map<String, Object> signer = new HashMap<>();
        signer.put("name", "John Doe");
        signer.put("position", "CEO");
        signer.put("email", "jhon@email.com");
        signer.put("signature", "https://placehold.co/280x70/F1F1F1/000?text=Signer%201&font=raleway");

        dataModel.put("signer", signer);

        template1.process(dataModel, stringWriter);

        // Create a PDF document using iText
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, pdfOutputStream);

        HtmlConverter.convertToPdf(stringWriter.toString(), pdfOutputStream);

        return pdfOutputStream.toByteArray();

    }

}
