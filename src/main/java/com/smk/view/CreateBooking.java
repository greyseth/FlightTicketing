package com.smk.view;

import com.smk.MainView;
import com.smk.dao.LocationDao;
import com.smk.model.Location;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Create Booking")
@Route(value = "create-book", layout = MainView.class)
public class CreateBooking extends VerticalLayout {
    private LocationDao locationDao;

    public CreateBooking() {
        locationDao = new LocationDao();
        createForm();
    }

    void createForm() {
        setAlignItems(Alignment.STRETCH);

        ComboBox<Location> fromDrop = new ComboBox("Dari: ");
        fromDrop.setItems(locationDao.getAll());
        fromDrop.setItemLabelGenerator(Location::getName);

        ComboBox<Location> destDrop = new ComboBox("Tujuan: ");
        destDrop.setItems(locationDao.getAll());
        destDrop.setItemLabelGenerator(Location::getName);

        DatePicker departDateP = new DatePicker("Tanggal keberangkatan: ");
        DatePicker arrivalDateP = new DatePicker("Tanggal kepulangan: ");
        Button searchBtn = new Button("Search");
        searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(fromDrop, destDrop, departDateP, arrivalDateP, searchBtn);
    }
}
