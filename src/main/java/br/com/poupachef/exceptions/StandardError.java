package br.com.poupachef.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;
	private String msg;
	private Long timesTamp;

}
