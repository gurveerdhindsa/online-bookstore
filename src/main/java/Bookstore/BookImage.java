package Bookstore;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Stores and retrieves images for Book instance
 */
public class BookImage {
    private static DB db;
    private static MongoClient mongoClient;
    private GridFS gfsPhoto;
    public BookImage(){
        mongoClient = new MongoClient(new MongoClientURI("ENTER DB URI"));
         db = mongoClient.getDB("online-bookstore");
         gfsPhoto = new GridFS(db, "photo");

    }

    /***
     * Saves the image into the Database
     * @param fileName
     * @param path
     * @throws IOException
     */
    public void saveImage(String fileName, String path) throws IOException {

        String newFileName = fileName;
        File imageFile = new File(path);
        GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
        gfsFile.setFilename(newFileName);
        gfsFile.save();

    }

    /**
     * Displays image details from the database
     */
    public void outputSavedImages() {
        DBCursor cursor = gfsPhoto.getFileList();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    /**
     * Queries the database to retrieve an image from the filename (Book name)
     * @param fileName
     */
    public  void getSingleImageExample(String fileName) {
        String newFileName = fileName;
        GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
        System.out.println(imageForOutput);
    }

    /**
     * Retrieves image and converts in file input Stream
     * @param fileName
     * @return
     */
    public InputStream retrieveImage(String fileName){
        GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);
        return imageForOutput.getInputStream();

    }

}
