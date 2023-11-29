package com.smk.view;

import com.smk.MainView;
import com.smk.model.Plane;
import com.smk.model.PlaneType;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

@Route(value = "planes", layout = MainView.class)
public class Planes extends VerticalLayout {
    public Planes() {
        add(
                createForm(),
                createGrid()
        );
    }

    Component createForm() {
        //i want to commit low tier god
        TextField nameField = new TextField("Aircraft Name");

        //Plane type initialization
        Collection<PlaneType> planeTypes = new LinkedList<>();
        planeTypes.add(new PlaneType("Economy"));
        planeTypes.add(new PlaneType("Business"));
        planeTypes.add(new PlaneType("First Class"));

        ComboBox<PlaneType> typeDrop = new ComboBox<>();
        typeDrop.setItems(planeTypes);
        typeDrop.setItemLabelGenerator(PlaneType::getType);

        NumberField capacityField = new NumberField("Capacity");

        Button addBtn = new Button("Add new aircraft");

        VerticalLayout formLayout = new VerticalLayout(nameField, typeDrop, capacityField);

        return formLayout;
    }

    Component createGrid() {
        Grid<Plane> planeGrid = new Grid<>(Plane.class);
        planeGrid.setColumns("id", "name", "type", "capacity");

        return planeGrid;
    }
}
