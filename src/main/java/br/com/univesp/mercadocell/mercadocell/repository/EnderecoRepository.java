package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnderecoRepository {
    //todo
    @Autowired
    JdbcTemplate jdbcTemplate;
    static final String COL_COD_ENDERECO			= "COD_ENDERECO";
    static final String COL_DSC_COMPLEMENTO         = "DSC_COMPLEMENTO";
    static final String COL_NRO_ENDERECO            = "NRO_ENDERECO";
    static final String COL_DSC_PONTO_REFERENCIA    = "DSC_PONTO_REFERENCIA";
    static final String COL_COD_PESSOA              = "COD_PESSOA";

    static final String CONSULTA_ENDERECO =
            "SELECT  ED.COD_ENDERECO, ED.DSC_COMPLEMENTO, ED.NRO_ENDERECO, ED.DSC_PONTO_REFERENCIA, ED.COD_CEP, ED.COD_PESSOA, " +
                    " L.COD_CEP, L.DSC_LOGRADOURO, B.COD_BAIRRO, B.NME_BAIRRO, " +
                    " M.COD_MUNICIPIO, M.NME_MUNICIPIO, E.COD_ESTADO, E.NME_ESTADO, E.SGL_UF " +
                    " FROM LOGRADOURO L " +
                    " INNER JOIN BAIRRO B  ON L.COD_BAIRRO = B.COD_BAIRRO " +
                    " INNER JOIN MUNICIPIO M ON M.COD_BAIRRO = B.COD_BAIRRO " +
                    " INNER JOIN ESTADO E  ON M.COD_ESTADO = E.COD_ESTADO " ;
    static final String FILTRO_CODIGO_ENDERECO = " WHERE COD_ENDERECO = ?";
    static final String FILTRO_CODIGO_PESSOA = " WHERE COD_PESSOA = ?";
    static final String UPDATE_ENDERECO = " UPDATE ENDERECO SET DSC_COMPLEMENTO = ?, NRO_ENDERECO = ? ," +
                                                " DSC_PONTO_REFERENCIA = ?, COD_CEP = ?, COD_PESSOA = ?" ;
    static final String DELETE_ENDERECO = " DELETE FROM ENDERECO " ;
    static final String INSERT_ENDERECO = " INSERT INTO ENDERECO (COD_ENDERECO, DSC_COMPLEMENTO, NRO_ENDERECO," +
                                                    " DSC_PONTO_REFERENCIA, COD_CEP, COD_PESSOA) " +
                                            " VALUES (?,?,?,?,?,?)";

    public void cadastrarEndereco(Endereco endereco) throws DataIntegrityViolationException{
        jdbcTemplate.update(INSERT_ENDERECO,
                endereco.getCodEndereco(),
                endereco.getDescricaoComplemento(),
                endereco.getNumeroEndereco(),
                endereco.getDescricaoPontoReferencia(),
                endereco.getLogradouro().getCodCEP(),
                endereco.getCodPessoa()
        );
    }

    public Endereco buscarEnderecoPorId(int codEndereco) {
        return jdbcTemplate.queryForObject(
                CONSULTA_ENDERECO + FILTRO_CODIGO_ENDERECO
                , (resultSet, rowNum) ->
                        new Endereco(
                                        resultSet.getInt(COL_COD_ENDERECO),
                                        resultSet.getString(COL_DSC_COMPLEMENTO),
                                        resultSet.getInt(COL_NRO_ENDERECO),
                                        resultSet.getString(COL_DSC_PONTO_REFERENCIA),
                                        resultSet.getInt(COL_COD_PESSOA),
                            new Logradouro(
                                    resultSet.getInt(LogradouroRepository.COL_COD_CEP),
                                    resultSet.getString(LogradouroRepository.COL_DSC_LOGRADOURO),
                                    resultSet.getString(LogradouroRepository.COL_DSC_COMPLEMENTO),
                                    new Bairro(
                                            resultSet.getInt(BairroRepository.COLUNA_COD_BAIRRO),
                                            resultSet.getString(BairroRepository.COLUNA_NME_BAIRRO),
                                            new Municipio(
                                                    resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                                    resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                                    new Estado(
                                                            resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                            resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO)
                                                    )
                                            )
                                    )
                            )
                        ),
                codEndereco
        );
    }

    public List<Endereco> listarEnderecos() {
        return jdbcTemplate.query(
                CONSULTA_ENDERECO
                , (resultSet, rowNum) ->
                        new Endereco(
                                resultSet.getInt(COL_COD_ENDERECO),
                                resultSet.getString(COL_DSC_COMPLEMENTO),
                                resultSet.getInt(COL_NRO_ENDERECO),
                                resultSet.getString(COL_DSC_PONTO_REFERENCIA),
                                resultSet.getInt(COL_COD_PESSOA),
                                new Logradouro(
                                        resultSet.getInt(LogradouroRepository.COL_COD_CEP),
                                        resultSet.getString(LogradouroRepository.COL_DSC_LOGRADOURO),
                                        resultSet.getString(LogradouroRepository.COL_DSC_COMPLEMENTO),
                                        new Bairro(
                                                resultSet.getInt(BairroRepository.COLUNA_COD_BAIRRO),
                                                resultSet.getString(BairroRepository.COLUNA_NME_BAIRRO),
                                                new Municipio(
                                                        resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                                        resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                                        new Estado(
                                                                resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                                resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO)
                                                        )
                                                )
                                        )
                                )
                        )
        );
    }

    public void atualizarEndereco(Endereco endereco) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                UPDATE_ENDERECO + FILTRO_CODIGO_ENDERECO,
                endereco.getDescricaoComplemento(),
                endereco.getNumeroEndereco(),
                endereco.getDescricaoPontoReferencia(),
                endereco.getLogradouro().getCodCEP(),
                endereco.getCodPessoa(),
                endereco.getCodEndereco()
        );
    }

    public void deletarEndereco(int codEndereco) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                DELETE_ENDERECO + FILTRO_CODIGO_ENDERECO,
                codEndereco
        );
    }

    public Endereco buscarEnderecoPorCodPessoa(int codPessoa) {
        return jdbcTemplate.queryForObject(
                CONSULTA_ENDERECO + FILTRO_CODIGO_ENDERECO
                , (resultSet, rowNum) ->
                        new Endereco(
                                resultSet.getInt(COL_COD_ENDERECO),
                                resultSet.getString(COL_DSC_COMPLEMENTO),
                                resultSet.getInt(COL_NRO_ENDERECO),
                                resultSet.getString(COL_DSC_PONTO_REFERENCIA),
                                resultSet.getInt(COL_COD_PESSOA),
                                new Logradouro(
                                        resultSet.getInt(LogradouroRepository.COL_COD_CEP),
                                        resultSet.getString(LogradouroRepository.COL_DSC_LOGRADOURO),
                                        resultSet.getString(LogradouroRepository.COL_DSC_COMPLEMENTO),
                                        new Bairro(
                                                resultSet.getInt(BairroRepository.COLUNA_COD_BAIRRO),
                                                resultSet.getString(BairroRepository.COLUNA_NME_BAIRRO),
                                                new Municipio(
                                                        resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                                        resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                                        new Estado(
                                                                resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                                resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO)
                                                        )
                                                )
                                        )
                                )
                        ),
                codPessoa
        );
    }
}
