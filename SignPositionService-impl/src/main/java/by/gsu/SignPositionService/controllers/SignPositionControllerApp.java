package by.gsu.SignPositionService.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.gsu.SignPositionService.client.db.postgresql.DBClient;
import by.gsu.SignPositionService.models.Point;
import by.gsu.SignPositionService.models.Sign;

@Controller
@RequestMapping("/")
public class SignPositionControllerApp implements SEISign {

	DBClient client = new DBClient();

	public static final Logger LOG = LoggerFactory.getLogger(SignPositionControllerApp.class);

	private static AtomicLong id = new AtomicLong(10);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "sign", method = RequestMethod.GET)
	public ResponseEntity<List<Sign>> methodGetListSigns(HttpServletResponse response) throws Exception {

		return ResponseEntity.ok(client.getListSigns());
	}

	@RequestMapping(value = "sign/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sign> methodGetSign(@PathVariable long id, final HttpServletResponse response) {
		List<Sign> store = new ArrayList<>();
		try {
			store = client.getListSigns();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Sign sign : store) {
			if (sign.getId() == id) {
				return ResponseEntity.ok(sign);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "sign", method = RequestMethod.POST)
	public ResponseEntity<Long> methodPostSign(@RequestBody Sign sign, final HttpServletResponse response) {
		long idTmp = id.incrementAndGet();
		sign.setId(idTmp);
		client.methodPostSign(sign);
		LOG.info("{}", sign);
		// return (ResponseEntity<Long>) ResponseEntity.ok(idTmp);
		return ResponseEntity.ok(idTmp);
	}

	@RequestMapping(value = "sign", method = RequestMethod.PUT)
	public ResponseEntity<Long> methodPutSign(@RequestParam Sign sign, final HttpServletResponse response) {
		LOG.info("{}", sign);
		List<Sign> store = new ArrayList<>();
		try {
			store = client.getListSigns();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < store.size(); i++) {
			Sign p = store.get(i);
			if (p.getId() == sign.getId()) {
				LOG.info("PUT: UPDATE; \n\t old:" + p + "\n\tnew:" + sign);
				client.methodPostSign(sign);
				return ResponseEntity.ok((long) i);
			}
		}

		long idTmp = id.incrementAndGet();
		sign.setId(idTmp);
		store.add(sign);
		LOG.info("PUT: CREATE; new id = " + idTmp + ";" + sign);

		LOG.info("{}", sign);
		System.out.println("lol");
		return (ResponseEntity<Long>) ResponseEntity.notFound();
		// public ResponseEntity<Void> methodPutSign(Sign sign,
		// HttpServletResponse response) {
		/*
		 * for (int index = 0; index < store.size(); index++) { if
		 * (store.get(index).getId() == sign.getId()) { store.set(index, sign); return
		 * (ResponseEntity<Void>) ResponseEntity.ok(); } } return (ResponseEntity<Void>)
		 * ResponseEntity.notFound();
		 */
	}

	@Override
	@RequestMapping(value = "sign/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> methodDeleteSign(@PathVariable long id, HttpServletResponse response) {
		List<Sign> store = new ArrayList<>();
		try {
			store = client.getListSigns();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Sign sign : store) {
			if (sign.getId() == id) {
				client.methodDeleteSign(id);
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "u", method = RequestMethod.GET)
	public @ResponseBody Sign upload(final HttpServletResponse response) {
		/*
		 * response.setHeader("Accept", "application/json");
		 * response.setHeader("Content-Type", "application/json");
		 */
		return new Sign(11, "aaa", "aaaaaaa", new Point(11, 22));
	}

	@ResponseBody
	@RequestMapping(value = "doc", method = RequestMethod.GET)
	public void userDataSklad(final HttpServletRequest request, final HttpServletResponse response) {
		response.setHeader("Accept", "application/json");
		response.setHeader("Content-Type", "application/json");
		String term = request.getParameter("term");

		response.setContentType("application/json");
		try {
			LOG.info("Data from ajax call " + term);

			String searchList = "ssssssssssss";
			response.getWriter().write(searchList);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Sign> handleAllUserRequest(final HttpServletResponse response) {
		List<Sign> store = new ArrayList<>();
		try {
			return client.getListSigns();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return store;
	}

}
