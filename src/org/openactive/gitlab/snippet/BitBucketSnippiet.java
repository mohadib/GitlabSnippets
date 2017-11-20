package org.openactive.gitlab.snippet;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class BitBucketSnippiet extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        System.out.println(e);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        System.out.println(anActionEvent);
    }
}
