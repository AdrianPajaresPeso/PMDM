/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.scrollingtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActionMode aMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //aqui se define que objeto se queda clicado para el contextual
        TextView article_text = findViewById(R.id.article);
        registerForContextMenu(article_text);


        //contextual Action Bar
        TextView subcabecera = findViewById(R.id.article_subheading);
        subcabecera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(aMode!= null){
                  return;
              }
              aMode = MainActivity.this.startActionMode(aModeCallBack);
              view.setSelected(true);
            }

        });


        Button mButton = findViewById(R.id.comment_button);
        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this,view);
                popup.getMenuInflater().inflate(R.menu.menu_context,popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.context_edit:
                                displayToast("Edit choice clicked.");
                                return true;
                            case R.id.context_share:
                                displayToast("Share choice clicked.");
                                return true;
                            case R.id.context_delete:
                                displayToast("Delete choice clicked.");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
    }
    private ActionMode.Callback aModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_action_bar,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.context_edit:
                    displayToast("Edit choice clicked.");
                    aMode.finish();
                    return true;
                case R.id.context_share:
                    displayToast("Share choice clicked.");
                    aMode.finish();
                    return true;
                case R.id.context_delete:
                    displayToast("Delete choice clicked.");
                    aMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            aMode = null;
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_edit:
                displayToast("Edit choice clicked.");
                return true;
            case R.id.context_share:
                displayToast("Share choice clicked.");
                return true;
            case R.id.context_delete:
                displayToast("Delete choice clicked.");
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void displayToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
