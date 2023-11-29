package com.smk.view;

import com.smk.MainView;
import com.smk.dao.BookingDao;
import com.smk.dao.LocationDao;
import com.smk.dao.ScheduleDao;
import com.smk.model.Location;
import com.smk.model.dto.ScheduleDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.Date;
import java.time.ZoneId;
import java.util.Collection;
import java.util.LinkedList;

@PageTitle("Create Booking")
@Route(value = "create-book", layout = MainView.class)
public class CreateBooking extends VerticalLayout {
    //Creates DAO variables
    private LocationDao locationDao;
    private ScheduleDao scheduleDao;
    private BookingDao bookingDao;
    private CreateBookingFormLayout bookingFormLayout;

    public CreateBooking() {
        //Initializes DAO objects
        locationDao = new LocationDao();
        scheduleDao = new ScheduleDao();
        bookingDao = new BookingDao();
        bookingFormLayout = new CreateBookingFormLayout();
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

        Grid<ScheduleDTO> scheduleGrid = new Grid<>(ScheduleDTO.class, false);
        scheduleGrid.addColumn(ScheduleDTO::getId).setHeader("Schedule Id");
        scheduleGrid.addColumn(ScheduleDTO::getDepartureId).setHeader("No. Pesawat");
        scheduleGrid.addColumn(ScheduleDTO::getArrivalId).setHeader("No. Keberangkatan");
        scheduleGrid.addColumn(ScheduleDTO::getDepartureDate).setHeader("Tanggal Keberangkatan");
        scheduleGrid.setItemDetailsRenderer(bookingFormLayout.createBookingRenderer());

        Button searchBtn = new Button("Search");
        searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchBtn.addClickListener(event -> {
            Collection<ScheduleDTO> schedules = scheduleDao.searchSchedule(
                    fromDrop.getValue().getId(),
                    destDrop.getValue().getId(),
                    (java.sql.Date)Date.from(departDateP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
            );

            scheduleGrid.setItems(schedules);
        });

        add(fromDrop, destDrop, departDateP, arrivalDateP, searchBtn, scheduleGrid);
    }
}
