package by.gsu.SignPositionService.controllers;

import java.util.List;

import by.gsu.SignPositionService.models.Sign;


public interface ISignRepository {

	public List<Sign> GetListSign();
	
	public Sign methodGetSign(long id);
	
	public void methodPostSign(Sign sign);

	public void methodPutSign(Sign sign);

	public void methodDeleteSign(long id);	
	

}
