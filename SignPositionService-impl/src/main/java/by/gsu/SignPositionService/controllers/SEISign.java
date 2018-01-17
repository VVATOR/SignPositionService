package by.gsu.SignPositionService.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.gsu.SignPositionService.models.Sign;

public interface SEISign {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index();

	@RequestMapping(value = "sign", method = RequestMethod.GET)
	public ResponseEntity<List<Sign>> methodGetListSigns(final HttpServletResponse response) throws Exception;

	@RequestMapping(value = "sign/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sign> methodGetSign(@PathVariable long id, final HttpServletResponse response)
			throws Exception;

	@RequestMapping(value = "sign", method = RequestMethod.POST)
	public ResponseEntity<Long> methodPostSign(@RequestBody Sign sign, final HttpServletResponse response)
			throws Exception;;

	@RequestMapping(value = "sign", method = RequestMethod.PUT)
	public ResponseEntity<Long> methodPutSign(@RequestParam Sign sign, final HttpServletResponse response)
			throws Exception;;

	@RequestMapping(value = "sign/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> methodDeleteSign(long id, HttpServletResponse response) throws Exception;;

	@RequestMapping(value = "u", method = RequestMethod.GET)
	public @ResponseBody Sign upload(final HttpServletResponse response) throws Exception;;

	@ResponseBody
	@RequestMapping(value = "doc", method = RequestMethod.GET)
	public void userDataSklad(final HttpServletRequest request, final HttpServletResponse response) throws Exception;;

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Sign> handleAllUserRequest(final HttpServletResponse response) throws Exception;;

}
