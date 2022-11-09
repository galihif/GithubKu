package com.giftech.githubku.data.remote.dto


import com.giftech.githubku.data.model.Repo
import com.google.gson.annotations.SerializedName

data class RepoDto(
    @SerializedName("assignees_url")
    val assigneesUrl: String,
    @SerializedName("blobs_url")
    val blobsUrl: String,
    @SerializedName("branches_url")
    val branchesUrl: String,
    @SerializedName("clone_url")
    val cloneUrl: String,
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String,
    @SerializedName("comments_url")
    val commentsUrl: String,
    @SerializedName("commits_url")
    val commitsUrl: String,
    @SerializedName("compare_url")
    val compareUrl: String,
    @SerializedName("contents_url")
    val contentsUrl: String,
    @SerializedName("contributors_url")
    val contributorsUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("default_branch")
    val defaultBranch: String,
    @SerializedName("deployments_url")
    val deploymentsUrl: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("disabled")
    val disabled: Boolean,
    @SerializedName("downloads_url")
    val downloadsUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("fork")
    val fork: Boolean,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("forks_url")
    val forksUrl: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String,
    @SerializedName("git_refs_url")
    val gitRefsUrl: String,
    @SerializedName("git_tags_url")
    val gitTagsUrl: String,
    @SerializedName("git_url")
    val gitUrl: String,
    @SerializedName("has_discussions")
    val hasDiscussions: Boolean,
    @SerializedName("has_downloads")
    val hasDownloads: Boolean,
    @SerializedName("has_issues")
    val hasIssues: Boolean,
    @SerializedName("has_pages")
    val hasPages: Boolean,
    @SerializedName("has_projects")
    val hasProjects: Boolean,
    @SerializedName("has_wiki")
    val hasWiki: Boolean,
    @SerializedName("homepage")
    val homepage: Any?,
    @SerializedName("hooks_url")
    val hooksUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_template")
    val isTemplate: Boolean,
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String,
    @SerializedName("issue_events_url")
    val issueEventsUrl: String,
    @SerializedName("issues_url")
    val issuesUrl: String,
    @SerializedName("keys_url")
    val keysUrl: String,
    @SerializedName("labels_url")
    val labelsUrl: String,
    @SerializedName("language")
    val language: Any?,
    @SerializedName("languages_url")
    val languagesUrl: String,
    @SerializedName("license")
    val license: Any?,
    @SerializedName("merges_url")
    val mergesUrl: String,
    @SerializedName("milestones_url")
    val milestonesUrl: String,
    @SerializedName("mirror_url")
    val mirrorUrl: Any?,
    @SerializedName("name")
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("notifications_url")
    val notificationsUrl: String,
    @SerializedName("open_issues")
    val openIssues: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("private")
    val `private`: Boolean,
    @SerializedName("pulls_url")
    val pullsUrl: String,
    @SerializedName("pushed_at")
    val pushedAt: String,
    @SerializedName("releases_url")
    val releasesUrl: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("ssh_url")
    val sshUrl: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("svn_url")
    val svnUrl: String,
    @SerializedName("tags_url")
    val tagsUrl: String,
    @SerializedName("teams_url")
    val teamsUrl: String,
    @SerializedName("topics")
    val topics: List<Any>,
    @SerializedName("trees_url")
    val treesUrl: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)

fun RepoDto.toRepo() =
    Repo(
        title = name,
        desc = description?:"Not Found",
        star = stargazersCount,
        updated = updatedAt
    )