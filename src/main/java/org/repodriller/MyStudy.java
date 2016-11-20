package org.repodriller;
import java.util.Arrays;

import org.repodriller.RepoDriller;
import org.repodriller.Study;
import org.repodriller.filter.commit.OnlyInBranches;
import org.repodriller.filter.commit.OnlyInMainBranch;
import org.repodriller.filter.commit.OnlyModificationsWithFileTypes;
import org.repodriller.filter.commit.OnlyNoMerge;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

public class MyStudy implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new MyStudy());
	}
	//private String [] header = { "name", "email", "mods"};
	@Override
	public void execute() {
		new RepositoryMining()
			.in(GitRepository.singleProject("C:/Users/dev/Documents/GitHub/repodriller"))
			.through(Commits.all())
			.process(new DiffVisitor(), new CSVFile("/Users/dev/Desktop/devs.csv"))//, new String []{"dasdas"}))
			.mine();
	}
}