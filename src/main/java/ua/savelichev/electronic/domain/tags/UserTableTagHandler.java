package ua.savelichev.electronic.domain.tags;


import org.apache.log4j.Logger;
import ua.savelichev.electronic.domain.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class UserTableTagHandler extends TagSupport {

    private static final Logger log = Logger.getLogger(UserTableTagHandler.class);

    List<User> users;
    String language;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Builds table of User objects
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {

        String[] langAndCounty = language.split("_");
        Locale locale = new Locale(langAndCounty[0], langAndCounty[1]);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);

        try {
            pageContext.getOut().print(
                    "<div>\n" +
                            "<table border=\"1\" align=\"center\">\n" +
                            "<tr>\n" +
                            "<th></th>\n" +
                            "<th></th>\n" +
                            "<th>" + resourceBundle.getString("email_address") + "</th>\n" +
                            "<th>" + resourceBundle.getString("login") + "</th>\n" +
                            "<th>" + resourceBundle.getString("cell_number") + "</th>\n" +
                            "<th>" + resourceBundle.getString("firstname") + "</th>\n" +
                            "<th>" + resourceBundle.getString("lastname") + "</th>\n" +
                            "<th>" + resourceBundle.getString("block_status") + "</th>\n" +
                            "<th>" + resourceBundle.getString("role") + "</th>\n" +
                            "</tr>\n");
            for (User user : users) {
                pageContext.getOut().print("" +
                        "<tr>\n" +
                        "    <td>\n" +
                        "       <div>\n" +
                        "        <form action=\"block-user\" method=\"post\">\n" +
                        "             <input type=\"hidden\" name=\"userEmail\" value=\"" + user.getEmail() + "\"/>\n" +
                        "             <button type=\"submit\">" + resourceBundle.getString("block") +"</button>\n" +
                        "        </form>\n" +
                        "        </div>\n" +
                        "    </td>\n" +
                        "    <td>\n" +
                        "        <div>\n" +
                        "            <form action=\"unblock-user\" method=\"post\">\n" +
                        "                <label><input hidden name=\"userEmail\" value=\"" + user.getEmail() + "\"/></label>\n" +
                        "                <button type=\"submit\">" + resourceBundle.getString("unblock") + "</button>\n" +
                        "            </form>\n" +
                        "        </div>\n" +
                        "    </td>\n" +
                        "    <td>" + user.getEmail() + "</td>\n" +
                        "    <td>" + user.getLogin() + "</td>\n" +
                        "    <td>" + user.getCellNumber() + "</td>\n" +
                        "    <td>" + user.getFirstName() + "</td>\n" +
                        "    <td>" + user.getLastName() + "</td>\n" +
                        "    <td>" + user.getBlocked() + "</td>\n" +
                        "    <td>" + user.getRole() + "</td>\n" +
                        "</tr>");
            }
        } catch (IOException e) {
            log.error("Exception " + e);
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
