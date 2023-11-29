package com.smk.view;

import com.smk.MainView;
import com.smk.dao.BookingDao;
import com.smk.model.Booking;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.LinkedList;

@PageTitle("Search booking")
@Route(value = "search", layout = MainView.class)
public class SearchBooking extends VerticalLayout {
    BookingDao bookingDao;

    public SearchBooking() {
        bookingDao = new BookingDao();

        createControls();
        createGrid();
    }

    public void createControls() {
        TextField searchField = new TextField("Search by name");
        Button searchBtn = new Button("Search");

        HorizontalLayout controlLayout = new HorizontalLayout(searchField, searchBtn);
        controlLayout.setWidthFull();
        controlLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        controlLayout.setAlignItems(Alignment.CENTER);
        controlLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(controlLayout);
    }

    public void createGrid() {
        Grid<Booking> bookingGrid = new Grid<>(Booking.class);
        bookingGrid.setColumns("id", "scheduleId", "pesawatId", "name", "price");

        add(bookingGrid);
    }
}
