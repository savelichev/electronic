package ua.savelichev.electronic.domain.tags;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.domain.services.StorageService;
import ua.savelichev.electronic.domain.services.product.IStorageService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class StorageTagHandler extends TagSupport {

    private static final Logger log = Logger.getLogger(StorageTagHandler.class);

    String article;

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public int doStartTag() throws JspException {

        IStorageService storageService = new StorageService();

        int amount = storageService.getProductAmountByArticle(Integer.valueOf(article));

        try {
            pageContext.getOut().print(amount);
        } catch (IOException e) {
            log.error("Exception "+e);
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
