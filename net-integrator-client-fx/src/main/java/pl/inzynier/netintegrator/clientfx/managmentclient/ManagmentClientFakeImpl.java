package pl.inzynier.netintegrator.clientfx.managmentclient;

import com.google.common.collect.Lists;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.ManagmentClientException;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingRead;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingWrite;

import java.util.List;

class ManagmentClientFakeImpl implements ManagmentClient {

    @Override
    public List<UrlMappingRead> getAll() throws ManagmentClientException {
        return Lists.newArrayList(
                new UrlMappingRead(1L, "/kot","GET","/cat","GET","localhost:4567"),
                new UrlMappingRead(2L, "/pies","GET","/dog","GET","localhost:9876"));
    }

    @Override
    public void create(UrlMappingWrite data) throws ManagmentClientException {

    }
}
