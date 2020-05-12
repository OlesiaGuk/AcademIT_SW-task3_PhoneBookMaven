package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.converter.IdArraysConverter;
import ru.academits.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.stream.Collectors;

public class DeleteContactServlet extends HttpServlet {
    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private IdArraysConverter idConverter = new IdArraysConverter();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String idJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            int[] idArray = idConverter.convertFromJson(idJson);

            phoneBookService.deleteContact(idArray);
        } catch (Exception e) {
            System.out.println("error in DeleteContactServlet POST: ");
            e.printStackTrace();
        }
    }
}
