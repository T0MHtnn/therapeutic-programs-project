INSERT INTO utilisateur (email, mdp, genre, pathologie, nom, prenom, age) VALUES
('test1@gmail.com', '$2a$10$kPNIzQvqbMOfc6Wn2wWbkuY9uiEQ3bTju7nJtt3OmTXRhG5yQnOMW', 'Homme', 'Diabète', 'Doe', 'John', 34),
('test2@gmail.com', '$2a$10$8.ZYYHbrhZaCo9udPeKCMe2SNvf4lEuupx3KAtJN4BpnrmflG/NBi', 'Femme', 'Hypertension', 'Smith', 'Jane', 29),
('test3@gmail.com', '$2a$10$i7qz9jsKSoyHHW8vzrYweuuvk27DCrf8CWtGwNKVw1WoG8slFjBLq', 'Femme', 'Asthme', 'Brown', 'Alice', 41),
('test4@gmail.com', '$2a$10$V8hArpfSxQV0p/ID9cjYaO.ZjwOQv4yk1AO261gpu79XRXhl9.KpW', 'Homme', 'Arthrite', 'White', 'Bob', 55),
('test5@gmail.com', '$2a$10$1mnewrnRbSiJGP.XjUNy2ulJJRpHDwROGOVt4RTdVMvq3PHx/Wz0i', 'Homme', 'Obésité', 'Green', 'Charlie', 47),
('daisy.blue@example.com', '$2a$10$Dc/5pTIxaHTvqIzskETPOesagE9JnnfNn2QAOcLyOyAHi6LCzWPg.', 'Femme', 'Migraines', 'Blue', 'Daisy', 36),
('eva.red@example.com', '$2a$10$pC9dTtuuRN1Di0MeipJWm.rgqoHbiQwLbWf7FjUaIPW6LXSZ4FYYC', 'Femme', 'Diabète', 'Red', 'Eva', 22),
('frank.yellow@example.com', '$2a$10$vc7GwzCF.zcRnMr.kvViBueDP/MRCNj3E13SA1s0BBcvVceVGi71e', 'Homme', 'Hypertension', 'Yellow', 'Frank', 60),
('george.purple@example.com', '$2a$10$.wKJmbp80QAlYsI17kLUeOynMW7/kquDvAXdlS3ClooVnE24WrSIG', 'Homme', 'Asthme', 'Purple', 'George', 39),
('hannah.orange@example.com', '$2a$10$aPLKsFeQmr.Zjku3rVmBDe0e/LnlYWyRNTZyXR48Zf.9Pg.LRqGQC', 'Femme', 'Migraines', 'Orange', 'Hannah', 26);

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

