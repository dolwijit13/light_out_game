#include<bits/stdc++.h>
using namespace std;

class light
{
public:
    int val;

    light()
    {
        val=0;
    }

    light(int o)
    {
        val=o;
    }

    light operator+(light o)
    {
        int tmp=this->val + o.val;
        return light(tmp%2);
    }

    light operator-(light o)
    {
        int tmp=this->val - o.val+2;
        return light(tmp%2);
    }

    light operator*(light o)
    {
        int tmp=this->val * o.val;
        return light(tmp%2);
    }

    void operator=(light o)
    {
        this->val = o.val;
    }

    void operator=(int o)
    {
        val = o;
    }

    bool operator==(light o)
    {
        return this->val == o.val;
    }

    bool operator==(int o)
    {
        return this->val == o;
    }
};

light a[270];
light b[270];
light m[270][270];
light im[270][270];
int n;

void builtM(int i,int x,int y)
{
    if(x<0 || y<0 || x==n || y==n)
        return;
    m[i][x*n+y]=1;
}

void mi(int p,int q)
{
    for(int j=0;j<n*n;j++)
    {
        m[p][j]=m[p][j]-m[q][j];
        im[p][j]=im[p][j]-im[q][j];
    }
}

void swp(int p,int q)
{
    for(int j=0;j<n*n;j++)
    {
        light tmp=m[p][j];
        m[p][j]=m[q][j];
        m[q][j]=tmp;

        tmp=im[p][j];
        im[p][j]=im[q][j];
        im[q][j]=tmp;
    }
}

int main()
{
    int i,j;
    int x,y;
    printf("n : ");
    scanf("%d",&n);

    printf("start board : \n");
    for(i=0;i<n*n;i++)
    {
        scanf("%d",&x);
        a[i]=x;
    }

    printf("final board : (input -1 if turn off every light)\n");
    for(i=0;i<n*n;i++)
    {
        scanf("%d",&x);
        x=0;
        if(x==-1)
        {
            for(j=0;j<n*n;j++)
                b[j]=0;
            break;
        }
        b[i]=x;
    }

    for(i=0;i<n*n;i++)
    {
        x=i/n;
        y=i%n;
        builtM(i,x,y);
        builtM(i,x-1,y);
        builtM(i,x,y-1);
        builtM(i,x+1,y);
        builtM(i,x,y+1);
        for(j=0;j<n*n;j++)
        {
            im[i][j]=0;
        }
        a[i]=a[i]-b[i];
        im[i][i]=1;
    }

    bool chk[270];
    for(i=0;i<n*n;i++)
        chk[i]=0;

    for(j=0;j<n*n;j++)
    {
        for(i=0;i<n*n;i++)
        {
            if(m[i][j]==1 && !chk[i])
            {
                chk[i]=1;
                for(int k=0;k<n*n;k++)
                {
                    if(i==k)
                        continue;
                    if(m[k][j]==1)
                    {
                        mi(k,i);
                    }
                }
            }
        }
    }


    for(i=0;i<n*n;i++)
    {
        if(m[i][i]==1)
            continue;
        for(j=0;j<n*n;j++)
        {
            if(m[i][j]==0)
                continue;
            swp(i,j);
            break;
        }
    }

    /*
    for(i=0;i<n*n;i++)
    {
        for(j=0;j<n*n;j++)
        {
            printf("%d ",m[i][j].val);
        }
        printf("\n");
    }

    printf("-------------\n");
    */

    for(i=0;i<n*n;i++)
    {
        for(j=0;j<n*n;j++)
        {
            printf("%d ",im[i][j].val);
        }
        printf("\n");
    }
    //*/

    //*
    queue<pair<int,int> >q;
    for(i=0;i<n*n;i++)
    {
        light ans=0;
        for(j=0;j<n*n;j++)
        {
            ans=ans+(im[i][j]*a[j]);
        }
        if(ans==1)
            q.push(make_pair(i/n,i%n));
    }

    printf("%d\n",q.size());
    while(!q.empty())
    {
        x=q.front().first;
        y=q.front().second;
        printf("(%d,%d) %d\n",x,y,x*n+y);
        q.pop();
    }
    //*/
}
