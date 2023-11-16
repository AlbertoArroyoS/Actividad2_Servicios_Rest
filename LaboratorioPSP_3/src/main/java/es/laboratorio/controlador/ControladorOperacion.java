package es.laboratorio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.laboratorio.modelo.entidad.Operacion;
import es.laboratorio.modelo.negocio.CalculosMatematicos;


@RestController
public class ControladorOperacion {

	@Autowired
	private CalculosMatematicos cm;
	
	@PostMapping(path="operacion_matematica",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Operacion> recibirOperacion(@RequestBody Operacion o) {
		System.out.println("recibirOperacion: objeto operacion: " + o);
		
		int resultado = 0;
		
		switch (o.getOperacion()) {
		case 1:
			resultado = cm.sumar(o.getNumero1(), o.getNumero2());
			break;
		case 2:
			resultado = cm.restar(o.getNumero1(), o.getNumero2());
			break;
		case 3:
			resultado = cm.multiplicar(o.getNumero1(), o.getNumero2());
			break;
		case 4:
			if(o.getNumero2() == 0) {
				return new ResponseEntity<Operacion>(o,HttpStatus.BAD_REQUEST);//400
			}
			resultado = cm.dividir(o.getNumero1(), o.getNumero2());
			break;
		default:
			break;
		}
		
		o.setResultado(resultado);		
		return new ResponseEntity<Operacion>(o,HttpStatus.OK);//201 CREATED
	}
}
