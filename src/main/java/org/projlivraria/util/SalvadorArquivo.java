package org.projlivraria.util;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component//Indica que é um componente gerenciável pelo framework. Poderá injetar
public class SalvadorArquivo {
	@Autowired
	private HttpServletRequest request;//Para recuperar pasta onde o caminho real no servidor
	
	public String escreve(String caminhoBase, MultipartFile arquivoSalvar) {//Método responsável por salvar o arquivo
		try {//Transfere o arquivo enviado p/ um arquivo no servidor
			String separadorSO = System.getProperty("file.separator");//Retorna o separador padrão do sistema por meio da JVM
			String nomeArqEnv = arquivoSalvar.getOriginalFilename();//O nome do arquivo como foi enviado no formulário
			String caminhoReal = request.getServletContext().getRealPath(separadorSO + caminhoBase);//Caminho absoluto no servidor
			String caminhoArquivo = caminhoReal + separadorSO + nomeArqEnv;//Concetena o caminho completo do arquivo no servidor
			arquivoSalvar.transferTo(Paths.get(caminhoArquivo).toFile());//Transfere o arquivo recebido para pasta no servidor
			return caminhoBase + separadorSO + nomeArqEnv;//Irá retornar o caminho relativo onde foi salvo no servidor p/ salvar no BD
		} catch(IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
