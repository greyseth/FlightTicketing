package com.smk;

import com.nimbusds.jose.crypto.impl.ECDH1PU;
import com.smk.view.CreateBooking;
import com.smk.view.Login;
import com.smk.view.Planes;
import com.smk.view.SearchBooking;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route
public class MainView extends AppLayout {

    public MainView() {
        createHeader();
        createDrawer();
    }

    void createHeader() {
        H1 logo = new H1("Flight booking");
        logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);

        Button accBtn = new Button("Log in");

        var header = new HorizontalLayout(new DrawerToggle(), logo);
        if (VaadinSession.getCurrent().getAttribute("username") == null)
            header.add(accBtn);
        else
            header.add(new H3("Logged in as "+VaadinSession.getCurrent().getAttribute("username")));
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();

        addToNavbar(header);
    }

    void createDrawer() {
        VerticalLayout drawerLayout = new VerticalLayout(
                new RouterLink("Book a Flight", CreateBooking.class),
                new RouterLink("Search Bookings", SearchBooking.class),
                new RouterLink("View Planes", Planes.class)
        );

        if (VaadinSession.getCurrent().getAttribute("username") != null) {
            drawerLayout.add(new RouterLink("Logout", Login.class));
        }

        addToDrawer(drawerLayout);
    }
}
