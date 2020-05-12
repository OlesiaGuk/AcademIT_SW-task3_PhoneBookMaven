package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.converter.ContactConverter;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

public class GetFilteredContactsServlet extends HttpServlet {
    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String term = req.getParameter("term").toUpperCase();
            List<Contact> filteredContactList = phoneBookService.getFilteredContacts(term);
            String filteredContactListJson = contactConverter.convertToJson(filteredContactList);
            resp.getOutputStream().write(filteredContactListJson.getBytes(Charset.forName("UTF-8")));
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (Exception e) {
            System.out.println("error in GetFilteredContactsServlet GET: ");
            e.printStackTrace();
        }
    }
}
