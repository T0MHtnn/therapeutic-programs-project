ALTER TABLE utilisateur MODIFY user_id INT DEFAULT 0;

INSERT INTO utilisateur (email, mdp, genre, pathologie, nom, prenom, age) VALUES
('test1@gmail.com', '$2a$10$kPNIzQvqbMOfc6Wn2wWbkuY9uiEQ3bTju7nJtt3OmTXRhG5yQnOMW', 'Homme', 'Diabète', 'Doe', 'John', 34),
('jane.smith@example.com', 'password456', 'Femme', 'Hypertension', 'Smith', 'Jane', 29),
('alice.brown@example.com', 'password789', 'Femme', 'Asthme', 'Brown', 'Alice', 41),
('bob.white@example.com', 'password321', 'Homme', 'Arthrite', 'White', 'Bob', 55),
('charlie.green@example.com', 'password654', 'Homme', 'Obésité', 'Green', 'Charlie', 47),
('daisy.blue@example.com', 'password987', 'Femme', 'Migraines', 'Blue', 'Daisy', 36),
('eva.red@example.com', 'password567', 'Femme', 'Diabète', 'Red', 'Eva', 22),
('frank.yellow@example.com', 'password1234', 'Homme', 'Hypertension', 'Yellow', 'Frank', 60),
('george.purple@example.com', 'password4321', 'Homme', 'Asthme', 'Purple', 'George', 39),
('hannah.orange@example.com', 'password5678', 'Femme', 'Migraines', 'Orange', 'Hannah', 26);

INSERT INTO activite (name, description, discipline, pathologies) VALUES
('Yoga doux', 'Yoga pour débutants', 'Yoga', 'Hypertension, Stress'),
('Pilates', 'Renforcement musculaire doux', 'Pilates', 'Douleurs lombaires'),
('Aquagym', 'Exercices en piscine', 'Natation', 'Arthrite'),
('Zumba', 'Danse fitness', 'Danse', 'Obésité'),
('Tai Chi', 'Relaxation et équilibre', 'Arts Martiaux', 'Stress, Migraines'),
('Randonnée', 'Activité en plein air', 'Marche', 'Diabète, Obésité'),
('Cyclisme', 'Exercice cardiovasculaire', 'Vélo', 'Hypertension, Diabète'),
('Musculation', 'Renforcement musculaire', 'Fitness', 'Général'),
('Méditation', 'Gestion du stress', 'Relaxation', 'Stress, Migraines'),
('Cours de natation', 'Apprentissage de la nage', 'Natation', 'Arthrite, Asthme');


INSERT INTO programme_therapeutique (nom, user_id) VALUES
('Programme Anti-Stress', 1),
('Programme Santé Cardiovasculaire', 2),
('Programme Mobilité Articulaire', 4),
('Programme Bien-Être', 6),
('Programme Minceur', 5),
('Programme Senior Actif', 8),
('Programme Jeune Actif', 3),
('Programme Endurance', 7),
('Programme Relaxation', 9),
('Programme Équilibre', 10);


INSERT INTO se_compose (activity_id, therapeutic_id) VALUES
(1, 1),
(9, 1),
(2, 2),
(7, 2),
(3, 3),
(6, 3),
(4, 4),
(5, 4),
(8, 5),
(7, 5),
(6, 6),
(2, 6),
(1, 7),
(9, 7),
(10, 8),
(5, 8),
(3, 9),
(9, 9),
(1, 10),
(8, 10);

INSERT INTO evaluation (user_id, activity_id, note) VALUES
(1, 1, 4),
(1, 2, 5),
(2, 3, 3),
(2, 4, 4),
(3, 5, 5),
(3, 6, 3),
(4, 7, 4),
(4, 8, 5),
(5, 9, 2),
(5, 10, 4),
(6, 1, 3),
(6, 2, 5),
(7, 3, 4),
(7, 4, 3),
(8, 5, 5),
(8, 6, 4),
(9, 7, 2),
(9, 8, 4),
(10, 9, 3),
(10, 10, 5);

