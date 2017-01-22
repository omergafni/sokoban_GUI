package model.receivers.save;

import model.data.level.Level;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveToObjFile implements SaveLevel {

	@Override
	public void save(Level level, String path) throws IOException {
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
		out.writeObject(level);
		out.close();

	}
}
