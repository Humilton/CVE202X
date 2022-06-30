package com.cve20xx;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Util {
    public static String runCmd (String cmd) {
        try {
            // Executes the command.
            Process process = Runtime.getRuntime().exec(cmd);
            return realRunCmd(process);
        } catch (IOException e) {
            Log.e("==>", e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Log.e("==>", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String runCmd (String[] cmd) {
        try {
            // Executes the command.
            Process process = Runtime.getRuntime().exec(cmd);
            return realRunCmd(process);
        } catch (IOException e) {
            Log.e("==>", e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Log.e("==>", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static <T> String realRunCmd(Process process) throws IOException, InterruptedException {
        // Reads stdout.
        // NOTE: You can write to stdin of the command using
        //       process.getOutputStream().
        int read;
        char[] buffer = new char[4096];

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        StringBuffer output = new StringBuffer();
        while ((read = reader.read(buffer)) > 0) {
            output.append(buffer, 0, read);
        }
        reader.close();

        BufferedReader stdError = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
        while ((read = stdError.read(buffer)) > 0) {
            output.append(buffer, 0, read);
        }
        stdError.close();

        // Waits for the command to finish.
        process.waitFor();

        Log.e("==>", output.toString());
        return output.toString();
    }
}
