package org.repodriller;

import java.util.HashMap;
import java.util.Map;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class ModificationsVisitor implements CommitVisitor {

	private Map<String, Integer> devs;
	
	public ModificationsVisitor() {
		this.devs = new HashMap<String, Integer>();
	}
	
	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		
		String dev = commit.getCommitter().getName();
		if(!devs.containsKey(dev)) devs.put(dev, 0);
		else {
		int currentFiles = devs.get(dev);
		devs.put(dev, commit.getModifications().size()); }
		for (Map.Entry<String, Integer> entry : devs.entrySet()){
			String developer = entry.getKey();
			Integer number = entry.getValue();
			
			
		writer.write(developer, number); }
		
	}
	
	@Override
	public String name() {
		return "files-per-dev";
	}

}