package org.example.dao;

import org.example.model.Interest;
import org.example.model.PersonDetails;

import java.util.List;

public interface IInterest {

    public Interest saveInterest(Interest interest);

    public Interest readInterest(String name);

    public List<Interest> readAllInterests();

    public Interest updateInterest(Interest interest);

    public Interest deleteInterest(Interest interest);
}
