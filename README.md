# SpeakEasy
Aplicativo de Ensino de Idiomas - Duolingo

Este projeto é um aplicativo de ensino de idiomas, inspirado no Duolingo, que oferece uma experiência interativa para usuários aprenderem novas línguas de forma dinâmica e gamificada. O sistema inclui cursos estruturados, exercícios interativos, reconhecimento de voz para prática de pronúncia e um sistema de recompensas para incentivar o aprendizado.
🛠️ Tecnologias Utilizadas

    Backend: Spring Boot + PostgreSQL

    Frontend: React Native (Expo ou CLI)

    Banco de Dados: PostgreSQL

    Autenticação: JWT + OAuth (Google, Apple)

    Armazenamento de Áudio: Firebase Storage ou AWS S3

    Reconhecimento de Voz: Google Speech-to-Text ou Web Speech API

    Gerenciamento de Estado: Redux ou Context API



 - Autenticação (Login / Register) - Segurança/Token

 - Banco de Dados - PostgreSQL
	Entidade:
		- Usuário (id, nome, email, senha, nível, xp, data criação).
		- Curso (id, nome, descrição, idioma_o, idioma_d, nível, duração, img, data_ini, ativo)
		- Exercício (Cada lição tem exercícios) - (id, licao_id, tipo, pergunta, resposta correta, opções, ordem)
		- Progresso_usuario (id, usuarrio_id, curso_id, licao_id, percentual concluído, xp_ganho, ultima atualização)
		- ranking (id, usuario_id, curso_id, xp_total)
		- recompensa (id, usuário_id, descrição, xp__necessária, data resgate)
		- pronuncia áudio (id, usuário_id, exercicio_idm audio__url, avaliação)
		- Licao (Cada curso tem varias licoes) - (id, curso_id, titulo, descrição, ordem, ativo)

 - Relacionamentos Principais

    Um Curso tem várias Lições.

    Uma Lição tem vários Exercícios.

    Um Usuário pode estar matriculado em vários Cursos e progredir em cada um separadamente.

    Cada Exercício pode ter diferentes formatos (múltipla escolha, tradução, áudio).

    O Progresso do Usuário armazena as lições concluídas e XP ganho.

    O Ranking mostra a pontuação dos usuários no curso.

    O Sistema de Recompensas incentiva a aprendizagem com desafios e prêmios.

    A Pronúncia Áudio armazena as respostas dos usuários para feedback automático.



frontend
 Autenticação

    Tela de Boas-Vindas → Explica rapidamente o app e tem botão para Login ou Cadastro.

    Tela de Login → Campos para email/senha e opção de login com Google/Apple.

    Tela de Cadastro → Cadastro de usuário (nome, email, senha, idioma nativo, idioma que quer aprender).

    Tela de Recuperação de Senha → Permite redefinir a senha via email.


 Navegação Principal (Bottom Tab Navigation)

    Home 

    Cursos 

    Progresso 

    Perfil 



 Telas do Curso

    Tela de Listagem de Cursos → Mostra os cursos disponíveis com imagem, nome e progresso.

    Tela de Detalhes do Curso → Descrição do curso, lista de lições e botão para iniciar.

    Tela de Listagem de Lições → Exibe as lições de um curso em ordem.

    Tela da Lição → Mostra os exercícios de uma lição.


Tipos de Exercícios

    Exercício de Múltipla Escolha → Usuário escolhe a resposta correta.

    Exercício de Tradução → Usuário escreve a resposta correta em outro idioma.

    Exercício de Áudio → O app lê uma frase, e o usuário deve repetir (com reconhecimento de voz).

    Exercício de Arrastar e Soltar → O usuário ordena palavras corretamente.

Progresso do Usuário

    Tela de Estatísticas → Mostra XP ganho, streaks (dias seguidos estudando) e progresso geral.

    Tela de Ranking → Classificação entre os usuários do mesmo curso.

    Tela de Conquistas → Recompensas desbloqueadas por desempenho.


 Perfil e Configurações

    Tela do Perfil → Exibe nome, foto, XP acumulado e botões para editar perfil.

    Tela de Configurações → Opções para idioma, notificações, tema claro/escuro, logout.



 Extras Possíveis

    Modo Offline → Permite fazer exercícios sem internet.

    Lojas e Itens Personalizáveis → Usuários compram avatares e personalizações com XP.

    Desafios e Batalhas → Permite competir com amigos em quizzes.

