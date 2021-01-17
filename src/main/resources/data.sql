DROP TABLE IF EXISTS base_station;
CREATE TABLE base_station
(
    base_station_id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    base_station_uuid          UUID,
    base_station_name          VARCHAR(255),
    coordinate_x               FLOAT(8),
    coordinate_y               FLOAT(8),
    detection_radius_in_meters FLOAT(8)
);

INSERT INTO base_station (base_station_uuid, base_station_name, coordinate_x, coordinate_y, detection_radius_in_meters)
VALUES ('90ed719c73ca42aaaf2df3d2c15b8b01', 'Base1', 5, 0, 10),
       ('90ed719c73ca42aaaf2df3d2c15b8b02', 'Base2', 6, 3, 8),
       ('90ed719c73ca42aaaf2df3d2c15b8b03', 'Base3', 7, 4, 5),
       ('90ed719c73ca42aaaf2df3d2c15b8b04', 'Base4', 8, 1, 3),
       ('90ed719c73ca42aaaf2df3d2c15b8b05', 'Base5', 9, 2, 2),
       ('90ed719c73ca42aaaf2df3d2c15b8b06', 'Base6', 1, 4, 1),
       ('90ed719c73ca42aaaf2df3d2c15b8b07', 'Base7', 2, 5, 15),
       ('90ed719c73ca42aaaf2df3d2c15b8b08', 'Base8', 3, 6, 20),
       ('90ed719c73ca42aaaf2df3d2c15b8b09', 'Base9', 4, 7, 13);