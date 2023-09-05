package org.example.dao;

import org.example.model.Interest;

import java.util.List;

public interface IInterestDAO {

    public Interest saveInterest(Interest interest);

    public Interest readInterest(String name);

    public List<Interest> readAllInterests();

    public Interest updateInterest(Interest interest);

    public Interest deleteInterest(Interest interest);
}
