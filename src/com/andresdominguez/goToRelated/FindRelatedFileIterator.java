package com.andresdominguez.goToRelated;

import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;

/**
 * @author andresdom@google.com (Andres Dominguez)
 */
public class FindRelatedFileIterator implements ContentIterator {

  private final String fileName;
  private final PsiManager psiManager;

  public FindRelatedFileIterator(String fileName, PsiManager psiManager) {
    this.fileName = fileName;
    this.psiManager = psiManager;
  }

  public boolean processFile(VirtualFile virtualFile) {
    if (fileName.equals(virtualFile.getName())) {
      psiManager.findFile(virtualFile).navigate(true);
    }
    return true;
  }
}
