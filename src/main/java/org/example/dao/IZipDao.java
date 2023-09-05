package org.example.dao;

import org.example.model.Zip;

import java.util.List;

public interface IZipDAO {
    public Zip saveZip(Zip zip);

    public Zip readZip(int id);

    public List<Zip> readAllZips();

    public Zip updateZip(Zip zip);

    public Zip deleteZip(Zip zip);
}
