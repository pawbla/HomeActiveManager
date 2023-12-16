CREATE TABLE weatherHistory (
	epochDateTimeStamp BIGINT NOT NULL,
	temperatureIn DECIMAL(10,2),
	humidityIn DECIMAL(10, 2),
	temperatureOut DECIMAL(10, 2),
	humidityOut DECIMAL(10, 2),
	pressure DECIMAL(10, 2),
	UNIQUE(epochDateTimeStamp)
);

