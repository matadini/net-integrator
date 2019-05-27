package pl.inzynier.netintegrator.clientfx.managmentclient;


import pl.inzynier.netintegrator.clientfx.managmentclient.dto.ManagmentClientException;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingRead;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingWrite;

import java.util.List;

public interface ManagmentClient {

    List<UrlMappingRead> getAll() throws ManagmentClientException;

    void create(UrlMappingWrite data) throws ManagmentClientException;

}

