package com.smk.view;

import com.smk.MainView;
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
    public CreateBooking() {
        createForm();
    }

    void createForm() {
        setAlignItems(Alignment.STRETCH);

        ComboBox fromDrop = new ComboBox("Dari: ");
        ComboBox destDrop = new ComboBox("Tujuan: ");
        DatePicker departDateP = new DatePicker("Tanggal keberangkatan: ");
        DatePicker arrivalDateP = new DatePicker("Tanggal kepulangan: ");
        Button searchBtn = new Button("Search");
        searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(fromDrop, destDrop, departDateP, arrivalDateP, searchBtn);
    }
}
