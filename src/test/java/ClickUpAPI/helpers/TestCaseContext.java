package ClickUpAPI.helpers;

import ClickUpAPI.domain.Folder;
import ClickUpAPI.domain.List;
import ClickUpAPI.domain.Task;
import lombok.Getter;
import lombok.Setter;

public class TestCaseContext {
    @Getter @Setter
    private static Folder folder;
    @Getter @Setter
    private static List list;
    @Getter @Setter
    private static Task task;
}
