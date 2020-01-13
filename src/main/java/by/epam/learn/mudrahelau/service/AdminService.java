package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;

import java.util.ArrayList;
import java.util.List;

public class AdminService {

    public void createClient(String login, String password, String name, String surname, TariffPlan tariffPlan) {

    }

    public void createTariffPlan(String title, int speed, double price) {

    }

    public void editClient(long id){

    }

    public void editTariffPlan(int id){

    }

    public List<Client> retrieveClients(){
        return new ArrayList<>();
    }

    public List<TariffPlan> retrieveTariffPlans(){
        return new ArrayList<>();
    }


}
