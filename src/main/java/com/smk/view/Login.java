package com.smk.view;

import com.smk.MainView;
import com.smk.dao.UserDao;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.awt.*;
import java.util.Collection;

@Route(value = "login", layout = MainView.class)
public class Login extends VerticalLayout {
    UserDao userDao;

    final LoginForm login = new LoginForm();

    public Login() {
        userDao = new UserDao();

        addClassName("login-view");
        setSizeFull();

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        login.addLoginListener(e -> {
            VaadinSession.getCurrent().setAttribute("username", e.getUsername());
        });

        add(new H1("Travelpedia"), login);
    }
}
