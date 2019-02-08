package com.google.android.gms.samples.vision.ocrproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class TextRecorder extends AppCompatActivity {
SharedPreferences preferences;
String value;
    EditText editText,filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recorder);
        //ImageButton imageButton = (ImageButton) findViewById(R.id.dial);
           filename = (EditText) findViewById(R.id.filename);
       OcrCaptureActivity ocrCaptureActivity = new OcrCaptureActivity();
           editText = (EditText) findViewById(R.id.textprint);
           preferences = getSharedPreferences("textnote",0);
           value =  preferences.getString("texts","");
           editText.setText(value);
//        Button clearbutton = (Button) findViewById(R.id.clearBtn);
//
//        clearbutton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //preferences.edit().clear().commit();
//                preferences = getSharedPreferences("textnote",0);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("texts",editText.getText());
//                preferences.edit().putString("texts",editText.getText());
//                // value =  preferences.getString("texts","");
//
//
//
//
//
//            }
//        });
    }

    public void dialNumber(View v) {
         editText = (EditText) findViewById(R.id.textprint);

        // Use format with "tel:" and phone number to create phoneNumber.
        String phoneNumber = String.format("tel: %s",
                "*902*"+editText.getText().toString()+"#");
        // Create the intent.
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        dialIntent.setData(Uri.parse(phoneNumber));
        // If package resolves to an app, send intent.
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Log.e("tag", "Can't resolve app for ACTION_DIAL Intent.");
        }
    }
    public void functionName(View v)
    {
        switch (v.getId())   // v is the button that was clicked
        {
            case (R.id.clearBtn):
                preferences.edit().clear().commit();

                editText.setText("");

                break;
            case (R.id.updateBtn):

                 preferences.edit().putString("texts",editText.getText().toString()).commit();

            break;
            default:
              System.out.print("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.pdf_id:
                Document doc = new Document();


                try {
                  // String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ADUREC";
                     String path = Environment.getExternalStorageDirectory().getPath();

                    File dir = new File(path);
                    if (!dir.exists())
                        dir.mkdirs();

                    Log.d("PDFCreator", "PDF Path: " + path);

                    //This is for random name
//                    ArrayList<Integer> number = new ArrayList<Integer>();
//                    for (int i = 1; i <= 10; ++i) number.add(i);
//                    Collections.shuffle(number);



                   // File file = new File(dir, "Document" + number + ".pdf");
                    //File file = new File(dir, "Document" +number+".pdf");
                   // File file = new File(dir,""+ filename.getText().toString().pdf");
                    File file = new File(dir,filename.getText().toString()+".pdf");

                    FileOutputStream fOut = new FileOutputStream(file);
                    PdfWriter.getInstance(doc, fOut);

                    //open the document
                    doc.open();

                    Paragraph p1 = new Paragraph("PDF created by Read Smartly app : ");
                    Font paraFont = new Font(Font.BOLD);
                    p1.setAlignment(Paragraph.ALIGN_CENTER);
                    p1.setFont(paraFont);

                    //add paragraph to document
                    doc.add(p1);


//                    Paragraph p3 = new Paragraph("OFFFER NUMBER : " + offer_number.getText().toString());
//                    Font paraFont3 = new Font(Font.COURIER, 14.0f, Color.GREEN);
//                    p3.setAlignment(Paragraph.ALIGN_CENTER);
//                    p3.setFont(paraFont2);
//
//                    doc.add(p3);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ncit_logo);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    Image myImg = Image.getInstance(stream.toByteArray());
                    myImg.setAlignment(Image.ALIGN_TOP);

//            add image to document
                    doc.add(myImg);

                    Paragraph p2 = new Paragraph("" + editText.getText().toString());
                    Font para = new Font(Font.COURIER);
                    p2.setAlignment(Paragraph.ALIGN_CENTER);
                    p2.setFont(para);

                    //add paragraph to document
                    doc.add(p2);//

                    //set footer
                    Phrase footerText = new Phrase("ADUREC DOCUMENT");
                    HeaderFooter pdfFooter = new HeaderFooter(footerText, true);
                    doc.setFooter(pdfFooter);

            Toast.makeText(this, "success pdf", Toast
                    .LENGTH_LONG).show();

                } catch (DocumentException de) {
                    Log.e("PDFCreator", "DocumentException:" + de);
                } catch (IOException e) {
                    Log.e("PDFCreator", "ioException:" + e);
                } finally {
                    doc.close();
                }


            case R.id.home_id:
                Intent i = new Intent(TextRecorder.this,HomePage.class);
                startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }
}
