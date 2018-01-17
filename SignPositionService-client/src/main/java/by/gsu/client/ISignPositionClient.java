package by.gsu.client;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import by.gsu.SignPositionService.models.Sign;

public interface ISignPositionClient {

    public String getHost();

    public void setHost(String host);

    public List<Sign> getListSigns() throws JsonParseException, JsonMappingException, IOException;

    public Sign methodGetSign(long id) throws JsonParseException, JsonMappingException, IOException;

    public void methodPostSign(Sign sign);

    public void methodPutSign(Sign sign);

    public void methodDeleteSign(long id);

}
