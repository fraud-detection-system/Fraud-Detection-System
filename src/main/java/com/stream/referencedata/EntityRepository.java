package com.stream.referencedata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;

class EntityRepository implements KVRepository<String, Object>{
	
	private final static Logger logger = LoggerFactory.getLogger(EntityRepository.class.getName());
	private final static String FILE_NAME = "reference-db";
	private final static String DIR_NAME = "/Users/bdutt/dev/bds/flink/streaming-with-flink";
	private File baseDir;
	private RocksDB db;

	void initialize() {
		RocksDB.loadLibrary();
		final Options options = new Options();
		options.setCreateIfMissing(true);
		baseDir = new File(DIR_NAME, FILE_NAME);
		try {
			Files.createDirectories(baseDir.getParentFile().toPath());
			Files.createDirectories(baseDir.getAbsoluteFile().toPath());
			db = RocksDB.open(options, baseDir.getAbsolutePath());
			logger.info("RocksDB initialized");
		} catch (IOException | RocksDBException e) {
			logger.error("Error initializng RocksDB. Exception: '{}', message: '{}'", e.getCause(), e.getMessage(), e);
		}
	}
	
	  public synchronized boolean save(String key, Object value) {
	    logger.info("saving value '{}' with key '{}'", value, key);
	    try {
	      db.put(key.getBytes(), SerializationUtils.serialize(value));
	    } catch (RocksDBException e) {
	      logger.error("Error saving entry. Cause: '{}', message: '{}'", e.getCause(), e.getMessage());
	      return false;
	    }
	    return true;
	  }
	  @Override
	  public synchronized Optional<Object> find(String key) {
	    Object value = null;
	    try {
	      byte[] bytes = db.get(key.getBytes());
	      if (bytes != null) value = SerializationUtils.deserialize(bytes);
	    } catch (RocksDBException e) {
	      logger.error(
	        "Error retrieving the entry with key: {}, cause: {}, message: {}", 
	        key, 
	        e.getCause(), 
	        e.getMessage()
	      );
	    }
	    logger.info("finding key '{}' returns '{}'", key, value);
	    return value != null ? Optional.of(value) : Optional.empty();
	  }
	  @Override
	  public synchronized boolean delete(String key) {
	    logger.info("deleting key '{}'", key);
	    try {
	      db.delete(key.getBytes());
	    } catch (RocksDBException e) {
	      logger.error("Error deleting entry, cause: '{}', message: '{}'", e.getCause(), e.getMessage());
	      return false;
	    }
	    return true;
	  }

	public static void main(String[] args) {
		EntityRepository entityRepository = new EntityRepository();
		entityRepository.initialize();
		entityRepository.save("test", "value1");
		System.out.println(entityRepository.find("test").get());
	}

}
