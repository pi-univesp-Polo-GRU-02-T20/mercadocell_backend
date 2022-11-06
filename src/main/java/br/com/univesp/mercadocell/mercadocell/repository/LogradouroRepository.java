package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.model.Logradouro;
import br.com.univesp.mercadocell.mercadocell.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogradouroRepository { 

    @Autowired
    JdbcTemplate jdbcTemplate;
    static final String COL_COD_CEP = "COD_CEP";
    static final String COL_DSC_LOGRADOURO = "DSC_LOGRADOURO";
    static final String COL_DSC_COMPLEMENTO = "DSC_COMPLEMENTO";
    static final String CONSULTA_LOGRADOURO =
            "SELECT  L.COD_CEP, L.DSC_LOGRADOURO, L.DSC_COMPLEMENTO, B.COD_BAIRRO, B.NME_BAIRRO, " +
                    " M.COD_MUNICIPIO, M.NME_MUNICIPIO, E.COD_ESTADO, E.NME_ESTADO, E.SGL_UF " +
                    " FROM LOGRADOURO L " +
                    " INNER JOIN BAIRRO B  ON L.COD_BAIRRO = B.COD_BAIRRO " +
                    " INNER JOIN MUNICIPIO M ON B.COD_MUNICIPIO = M.COD_MUNICIPIO " +
                    " INNER JOIN ESTADO E  ON M.COD_ESTADO = E.COD_ESTADO " ;
    static final String FILTRO_CEP_LOGRADOURO = " WHERE COD_CEP = ?";
    static final String UPDATE_LOGRADOURO = " UPDATE LOGRADOURO SET COD_CEP = ?, DSC_LOGRADOURO = ? " ;
    static final String DELETE_LOGRADOURO = " DELETE FROM LOGRADOURO " ;
    static final String INSERT_LOGRADOURO = "INSERT INTO LOGRADOURO (COD_CEP, DSC_LOGRADOURO, DSC_COMPLEMENTO) " +
            "VALUES (?, ?)";

    public void cadastrarLogradouro(Logradouro logradouro) throws DataIntegrityViolationException {
           jdbcTemplate.update(INSERT_LOGRADOURO,
                            logradouro.getCodCEP(),
                            logradouro.getDescricaoLogradouro()                            
            );
    }

    public Logradouro buscarLogradouroPorId(int idLogradouro) {
            return jdbcTemplate.queryForObject(
                    CONSULTA_LOGRADOURO + FILTRO_CEP_LOGRADOURO
                    , (resultSet, rowNum) ->
                        new Logradouro(
                                resultSet.getInt(COL_COD_CEP),
                                resultSet.getString(COL_DSC_LOGRADOURO),
                                resultSet.getString(COL_DSC_COMPLEMENTO),
                                new Bairro(
                                        resultSet.getInt(BairroRepository.COLUNA_COD_BAIRRO),
                                        resultSet.getString(BairroRepository.COLUNA_NME_BAIRRO),
                                        new Municipio(
                                                resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                                resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                                new Estado(
                                                        resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                        resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO),
                                                        resultSet.getString(EstadoRepository.COLUNA_SGL_UF)
                                                )
                                        )
                                )
                            ),
                    idLogradouro
            );
    }

    public List<Logradouro> listarLogradouros() {
        return jdbcTemplate.query(
                CONSULTA_LOGRADOURO
                , (resultSet, rowNum) ->
                        new Logradouro(
                                resultSet.getInt(COL_COD_CEP),
                                resultSet.getString(COL_DSC_LOGRADOURO),
                                resultSet.getString(COL_DSC_COMPLEMENTO),
                                new Bairro(
                                        resultSet.getInt(BairroRepository.COLUNA_COD_BAIRRO),
                                        resultSet.getString(BairroRepository.COLUNA_NME_BAIRRO),
                                        new Municipio(
                                                resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                                resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                                new Estado(
                                                        resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                        resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO),
                                                        resultSet.getString(EstadoRepository.COLUNA_SGL_UF)
                                                )
                                        )
                                )
                        )
        );
    }

    public void atualizarLogradouro(Logradouro logradouro) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                UPDATE_LOGRADOURO + FILTRO_CEP_LOGRADOURO,
                logradouro.getCodCEP(),
                logradouro.getDescricaoLogradouro()
        );
    }

    public void deletarLogradouro(int idLogradouro) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                DELETE_LOGRADOURO + FILTRO_CEP_LOGRADOURO,
                idLogradouro
        );
    }

}
