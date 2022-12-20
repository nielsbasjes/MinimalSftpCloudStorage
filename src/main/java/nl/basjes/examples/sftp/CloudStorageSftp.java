package nl.basjes.examples.sftp;

import org.apache.sshd.common.file.FileSystemFactory;
import org.apache.sshd.common.session.SessionContext;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.sftp.server.SftpFileSystemAccessor;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class CloudStorageSftp {

    private static final Logger LOG = LoggerFactory.getLogger(CloudStorageSftp.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length == 0) {
            LOG.error("Must specify the bucket to connect to.");
            return;
        }

        String bucketName = args[0];

        try(SshServer server = SshServer.setUpDefaultServer()) {
            server.setPort(2222);

            server.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(new File("my.pem").toPath()));

            SftpSubsystemFactory sftpSubsystemFactory = new SftpSubsystemFactory();
            sftpSubsystemFactory.setFileSystemAccessor(SftpFileSystemAccessor.DEFAULT);
            server.setSubsystemFactories(Collections.singletonList(sftpSubsystemFactory));

            server.setFileSystemFactory(new FileSystemFactory() {
                @Override
                public Path getUserHomeDir(SessionContext session) {
                    return Path.of("/");
                }

                @Override
                public FileSystem createFileSystem(SessionContext session) {
                    return FileSystems.getFileSystem(URI.create("gs://" + bucketName));
                }
            });

            server.start();

            // Only for testing, 10000 seconds is fine.
            sleep(10000000L);
        }
    }
}
