package org.repodriller;

import java.util.HashSet;
import java.util.Set;
import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class CommitersVisitor implements CommitVisitor {

	private Set<String> devs;
	public CommitersVisitor() {
		this.devs = new HashSet<String>();
	}
	
	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		
		String dev = commit.getCommitter().getName();
		if(!devs.contains(dev)) devs.add(dev);
		
			
		writer.write(devs); 

		}
		
		


	@Override
	public String name() {		
		return "unique-devs";
	}
}