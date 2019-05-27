package pl.inzynier.netintegrator.clientfx.managmentclient;

import com.google.common.collect.Lists;
import lombok.Builder;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.ManagmentClientException;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingRead;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingWrite;

import java.util.List;

@Builder
class ManagmentClientHttpImpl implements ManagmentClient {


    @Override
    public List<UrlMappingRead> getAll() throws ManagmentClientException {
        return Lists.newArrayList();
    }

    @Override
    public void create(UrlMappingWrite data) throws ManagmentClientException {

    }
}
