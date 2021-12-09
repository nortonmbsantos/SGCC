# Sistema Gerenciador de Custos para Condominios (SGCC)

Sistema desenvolvido por Norton Matias Bino dos Santos para o Trabalho de Conclusão de Curso

## Resumo Monografia
Os sistemas de informação tem ganhado cada vez mais espaço na vida das pessoas, tanto na vida pessoal, quanto na profissional. Sendo assim, muitas soluções tem surgido para auxiliar na resolução de problemas, como o caso da administração, com os chamados \textit{ERPs}. Estes sistemas possibilitam o armazenamento de dados, estes que podem ser nomes, idades, valores, etc. O processamento destes dados permite a apresentação de informações, que auxiliam o usuário na tomada de decisões. Este documento propõe o desenvolvimento de um sistema, que ajude na administração de um condomínio, permitindo o cadastro de moradores, despesas, reserva das áreas comuns, advertências e multas, aos moradores. O sistema também irá permitir que os moradores acessem um módulo próprio, para que possam visualizar a taxa condominial do mês atual, assim como as taxas anteriores. Além disso, o sistema também proverá relatórios e gráficos, provenientes dos dados armazenados pelo administrador, que pretende auxiliar em tomadas de decisões.

## Tecnologias utilizadas
Java, Spring Boot, Hibernate, CSS, Javascript, MySQL;

## Configurações

### Banco de dados e testes

Para configurar o banco de dados é substituir o carregamento das cofigurações no pacote e arquivo br.edu.utfpr.sgcc.config.DBConfig
#### Para servidor foi utilizado o MariaDB/MySQL pelo:
registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
#### Para testes é utilizado um banco em memória pelo:
registry = new StandardServiceRegistryBuilder().configure("h2test.cfg.xml").build();


## Para execução do sistema é necessário que o projeto seja compilado para um .war ou .jar ou dependendo das solicitações do servidor que facilite a implantação do SpringBoot 