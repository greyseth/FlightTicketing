package com.smk.view;

import com.smk.dao.BookingDao;
import com.smk.model.Booking;
import com.smk.model.dto.ScheduleDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class CreateBookingFormLayout extends FormLayout {
    final BookingDao bookingDao;

    final TextField idField = new TextField();
    final TextField fromField = new TextField("Dari:");
    final TextField toField = new TextField("To:");
    final DatePicker departDateP = new DatePicker("Tanggal Keberangkatan");
    final TextField nameField = new TextField("Nama:");
    final TextField priceField = new TextField("Harga:");
    final Button saveBtn = new Button("Simpan booking");

    public CreateBookingFormLayout() {
        bookingDao = new BookingDao();
    }

    public void createFormLayout() {
        idField.setVisible(false);
        add(idField);

        fromField.setReadOnly(true);
        toField.setReadOnly(true);

        departDateP.setReadOnly(true);

        Stream.of(fromField, toField, departDateP, nameField, priceField, saveBtn).forEach(this::add);

        saveBtn.addClickListener(event -> {
            Booking booking = new Booking();

            booking.setScheduleId(Long.parseLong(idField.getValue()));
            booking.setName(nameField.getValue());
            booking.setPrice(Double.parseDouble(priceField.getValue()));
            Optional<Integer> id = bookingDao.save(booking);
            id.ifPresent(i -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setText("Booking data created with id "+id);
                dialog.setCancelable(false);
                dialog.setRejectable(false);
                dialog.setConfirmText("Ok");
                dialog.addConfirmListener(e -> {
                    dialog.close();
                });

                add(dialog);
                dialog.open();
            });
        });
    }

    public void setScheduleDTO(ScheduleDTO dto) {
        idField.setValue(String.valueOf(dto.getId()));
        fromField.setValue(dto.getDepartureId());
        toField.setValue(dto.getArrivalId());
        departDateP.setValue(LocalDate.parse(dto.getDepartureDate().toString()));
    }

    static ComponentRenderer<CreateBookingFormLayout, ScheduleDTO> createBookingRenderer() {
        return new ComponentRenderer<>(CreateBookingFormLayout::new, CreateBookingFormLayout::setScheduleDTO);
    }
}
