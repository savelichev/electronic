package ua.savelichev.electronic.domain.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CartTagHandler extends TagSupport {

    private String cartItemId;

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    @Override
    public int doStartTag() throws JspException {

        return SKIP_BODY;
    }
}
