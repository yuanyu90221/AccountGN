# AccountGN
<ol>
	<li>mainEntry: 
		<ul>
			<li>GUI: PGNLayout.java</li>
			<li>Daemon: PGenerator.java</li>
		</ul>
	</li>
	<li>execute script: 
	   <ul>
			<li>
				Windows Version:
				<ul>
					<li>GUI: start.bat</li>
					<li>Daemon: startDaemon.bat</li>
				</ul>
			</li>
			<li>
				Linux Version:
				<ul>
					<li>GUI: start.sh</li>
					<li>Daemon: startDaemon.sh</li>
				</ul>
			</li>
		</ul> 
	</li>
	<li>configuration file: config.properties</li>
	<li> # required files: those file are encoding in UTF8 with BOM
		<ol>
			<li>surname</li>
			<li>chiname</li>
			<li>englishName</li>
		</ol>
	</li>
	<li> Usage:
		<ul>
		  	<li>for GUI:
			   <ol>
			   		<li>create a folder</li>
			   		<li>copy pag-*.jar into folder</li>
			   		<li>rename pag-*.jar into pag.jar</li>
			   		<li>copy config folder into folder</li>
			   		<li>make sure config contains above required files</li>
			   		<li>run start.bat(on windows) or run start.sh(on linux)</li>
			   </ol>
		   </li>
		   <li>for Daemon: 
			   <ol>
			   		<li>create a folder</li>
			   		<li>copy pagDaemon-*.jar into folder</li>
			   		<li>rename pagDaemon-*.jar into pag.jar</li>
			   		<li>copy config folder into folder</li>
			   		<li>make sure config contains above required files</li>
			   		<li>run startDaemon.bat(on windows) or run startDaemon.sh(on linux)</li>
			   </ol>
		   </li>
 		<ul>
	</li>
</ol>
