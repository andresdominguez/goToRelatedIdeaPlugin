package com.andresdominguez.goToRelated;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

/**
 * @author andresdom@google.com (Andres Dominguez)
 */
public class GoToRelatedAction extends AnAction {

  public void actionPerformed(AnActionEvent e) {
  }

  @Override
  public void update(AnActionEvent e) {
    PsiFile file = e.getData(LangDataKeys.PSI_FILE);
    Editor editor = e.getData(PlatformDataKeys.EDITOR);

    // Disable the option if there is no file or editor.
    if (file == null || editor == null) {
      e.getPresentation().setEnabled(false);
      return;
    }

    final String fileName = file.getName();

    // Ignore non-js files.
    if (fileName.endsWith(".js") == false) {
      return;
    }

    FileIterator fileIterator = new FileIterator(file, PsiManager.getInstance(editor.getProject()));
    ProjectRootManager.getInstance(editor.getProject()).getFileIndex().iterateContent(fileIterator);
  }
}
