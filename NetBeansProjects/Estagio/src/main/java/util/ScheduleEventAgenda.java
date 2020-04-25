/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidades.AgendamentoHorario;
import java.util.Date;
import org.primefaces.model.DefaultScheduleEvent;
/**
 *
 * @author marcos-ladeira
 */
    public class ScheduleEventAgenda extends DefaultScheduleEvent{
    private AgendamentoHorario agendamentoHorario;

    public AgendamentoHorario getAgendamentoHorario() {
        return agendamentoHorario;
    }

    public void setAgendamentoHorario(AgendamentoHorario agendamentoHorario) {
        this.agendamentoHorario = agendamentoHorario;
    }

    public ScheduleEventAgenda() {
    }

    public ScheduleEventAgenda(String title, Date start, Date end) {
        super(title, start, end);
    }

    public ScheduleEventAgenda(String title, Date start, Date end, boolean allDay) {
        super(title, start, end, allDay);
    }

    public ScheduleEventAgenda(String title, Date start, Date end, String styleClass) {
        super(title, start, end, styleClass);
    }

    public ScheduleEventAgenda(String title, Date start, Date end, Object data) {
        super(title, start, end, data);
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        return super.getUrl(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return super.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEditable() {
        return super.isEditable(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setData(Object data) {
        super.setData(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getData() {
        return super.getData(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStyleClass() {
        return super.getStyleClass(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStyleClass(String styleClass) {
        super.setStyleClass(styleClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAllDay(boolean allDay) {
        super.setAllDay(allDay); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAllDay() {
        return super.isAllDay(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEndDate(Date endDate) {
        super.setEndDate(endDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getEndDate() {
        return super.getEndDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStartDate(Date startDate) {
        super.setStartDate(startDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getStartDate() {
        return super.getStartDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitle() {
        return super.getTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(String id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
}